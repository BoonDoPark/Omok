package com.example.omok.bot;

import com.example.omok.deserialize.Deserialization;
import com.example.omok.packet.Packet;
import com.example.omok.packet.PacketType;
import com.example.omok.serialize.Serialization;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class OmokBot implements Runnable{
    private final String host = "localhost";
    private final int port = 8082;

    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private final Serialization serialization = new Serialization();
    private final Deserialization deserialization = new Deserialization();

    // Bot's state
    private int[][] board = new int[19][19];
    private boolean isMyTurn = false;
    private int myColor = -1; // 0 for Black, 1 for White

    private int lastMoveX = -1;
    private int lastMoveY = -1;

    public OmokBot() {
        initializeBoard();
    }

    @Override
    public void run() {
        try {
            socket = new Socket(host, port);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();

            sendReadyPacket();
            listenToServer();

        } catch (IOException e) {
            System.out.println("Connection closed or error: " + e.getMessage());
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendReadyPacket() throws IOException {
        Packet packet = new Packet(PacketType.READY,
                0,
                0,
                0,
                0,
                0,
                ""
        );
        outputStream.write(serialization.serializePacket(packet));
        outputStream.flush();
    }

    private void listenToServer() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;

        // TODO: 패킷이 들어오지 않으면 봇은 아무런 동작을 하지 않는다. (수동적으로 동작하기 때문에 부하가 걸릴까..?)
        while (true) {
            try {
                if ((bytesRead = inputStream.read(buffer)) == -1) continue;

                byte[] byteBuffer = Arrays.copyOf(buffer, bytesRead);
                Packet receivedPacket = deserialization.deserializePacket(byteBuffer);
                handlePacket(receivedPacket); // TODO: 패킷을 수신하고 처리만 하고 봇이 다시 보내지 않는걸로 보임. (단일 책임의 원칙 준수)
            } catch (Exception e) {
                System.err.println("Error handling packet: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void handlePacket(Packet packet) throws IOException {
        switch (packet.getPacketType()) {
            case READY:
                handleReadyPacket(packet);
                break;

            case GAMESTART:
                handleGameStartPacket(packet);
                break;

            case COORDINATE:
                handleCoordinatePacket(packet);
                break;

            case DISCRIMINATE:
                handleDiscriminatePacket(packet);
                sendReadyPacket();
                break;

            case COORDINATE_FAIL: // PACKET_FAIL 처리 추가
                handleCoordinateFail(packet);
                break;
        }
    }

    private void handleReadyPacket(Packet packet) {
        if (myColor == -1) {
            myColor = packet.getPlayerColor();
            initializeBoard();
        }
    }

    private void handleGameStartPacket(Packet packet) throws IOException {
        if (myColor == 0) {
            isMyTurn = true;
            sendCoordinatePacket(packet);
        }
    }

    private void handleCoordinatePacket(Packet packet) throws IOException {
        int color = packet.getPlayerColor();
        int x = packet.getX();
        int y = packet.getY();

        if (x >= 0 && x < 19 && y >= 0 && y < 19) {
            board[y][x] = color;
        }

        if (color != myColor) {
            isMyTurn = true;
            sendCoordinatePacket(packet);
        }
    }

    private void handleDiscriminatePacket(Packet packet) throws IOException {
        outputStream.write(
                serialization.serializePacket(
                        new Packet(
                                PacketType.INITIALIZE, // 얘만 봄
                                0,
                                0,
                                0,
                                0,
                                0,
                                ""
                        )
                )
        );
        outputStream.flush();
        isMyTurn = false;
        myColor = -1;
        initializeBoard();
    }

    private void handleCoordinateFail(Packet errorPacket) {
        if (board[lastMoveY][lastMoveX] == myColor) {
            board[lastMoveY][lastMoveX] = -1;
        }
        System.out.println("PACKET_FAIL: 잘못된 차례입니다. (" + lastMoveX + ", " + lastMoveY + ") 좌표를 되돌립니다.");

        // 3. 봇은 다시 움직일 수 있습니다 (isMyTurn은 이미 true 상태).
        lastMoveX = -1;
        lastMoveY = -1;

        // 이 상황에서는 봇이 즉시 다시 올바른 수를 둘 수 있도록 처리하는 것이 좋습니다.
        try {
            // 서버로부터 에러를 받았으므로, 잠시 후 (딜레이 없이) 다시 시도
            Thread.sleep(1000);
            sendCoordinatePacket(errorPacket);
        } catch (IOException e) {
            System.err.println("Failed to resend coordinate after PACKET_FAIL: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initializeBoard() {
        for (int[] row : this.board) {
            Arrays.fill(row, -1);
        }
    }

    private List<Integer> getCoordinate() {
        List<Integer> coordinateInfo = new ArrayList<>();
        Random random = new Random();
        int x, y;

        do {
            x = random.nextInt(19);
            y = random.nextInt(19);
        } while (board[y][x] != -1);

        coordinateInfo.add(x);
        coordinateInfo.add(y);

        return coordinateInfo;
    }
    private void sendCoordinatePacket(Packet packet) throws IOException {
        if (!isMyTurn) return;

        List<Integer> coordinateInfo = this.getCoordinate();

        board[coordinateInfo.get(1)][coordinateInfo.get(0)] = myColor;

        lastMoveX = coordinateInfo.get(0);
        lastMoveY = coordinateInfo.get(1);

        outputStream.write(
                serialization.serializePacket(
                        new Packet(
                                PacketType.COORDINATE,
                                0,
                                0,
                                coordinateInfo.get(0),
                                coordinateInfo.get(1),
                                myColor,
                                ""
                        )
                )
        );
        outputStream.flush();
        isMyTurn = false;
    }
}
