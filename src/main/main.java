package main;

import house.House;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class main {

private House[] house;
private Scanner scanner = new Scanner(System.in);
private String[] streets = {"Pushkinskaya","Vodoprovodnaya","Shosseynaya","Sovetskaya","Central","Voennaya","Pushkinskaya","Sobornaya","Dekabristov"};

public static void main(String[] args) {
        main prog = new main();
        prog.run();
}

private void run() {
        house = createEmptyHouseArray(100);
        UserInput(false);
        room();
        Floor();
        space();
}

private void UserInput(boolean exit) {
        if (exit) return;
        Scanner input = new Scanner(System.in);
        while (true) {
                System.out.println("Введите: 'p' - чтобы вывести массив; 'r' - чтобы заполнить массив рандомными домами,'f' - чтобы сохранить массив в файл,'b' - чтобы сохранить массив в бинарный файл,'g'- чтобы считать массив домов из файла, 'k' - чтобы считать массив домов из бинарного файла,'e' -  чтобы выйти с программы");
                String in = input.nextLine();
                if (in.charAt(0) == 'p') {
                        for (int i = 0; i < house.length; i++) {
                                System.out.println(house[i].toString());
                        }
                } else if (in.charAt(0) == 'r') {
                        fillHousesArrRandomly(house);
                } else if(in.charAt(0) == 'f') {
                        System.out.println("Введите имя файла:");
                        String path = input.nextLine();
                        writeInFile(house, path+".txt");
                } else if (in.charAt(0) == 'b') {
                        System.out.println("Введите имя бинарного файла:");
                        String path = input.nextLine();
                        writeInFileBinary(house, path+".bin");
                } else if (in.charAt(0) == 'g'){
                        System.out.println("Введите имя файла:");
                        String path = input.nextLine();
                        readFromFile(house,path+".txt");
                }else if (in.charAt(0) == 'k'){
                        System.out.println("Введите имя бинарного файла:");
                        String path = input.nextLine();
                        readFromFileBinary(house,path+".bin");
                }else if (in.charAt(0) == 'e') {
                        exit = true;
                        break;
                }
        }
        UserInput(exit);
}

private void room() {
        System.out.println("Input room = ");
        int room1 = scanner.nextInt();
        for (int i = 0; i < house.length; i++) {
                if (house[i].getRoom()==room1) {
                        System.out.println(house[i].toString());
                }
        }
}

private void Floor() {
        System.out.println("Input room = ");
        int room2 = scanner.nextInt();
        System.out.println("Input first num span floor  = ");
        int a = scanner.nextInt();
        System.out.println("Input second num span floor  = ");
        int b = scanner.nextInt();
        for (int i = 0; i < house.length; i++) {
        if (a <= house[i].getFloor() && house[i].getFloor() <= b && house[i].getRoom() == room2)
        System.out.println(house[i].toString());
        }
}

private void space() {
        System.out.println("Input space = ");
        float space1 = scanner.nextFloat();
        for (int i = 0; i < house.length; i++) {
        if (house[i].getSpace()>space1) {
        System.out.println(house[i].toString());
        }
        }
}

        private void fillHousesArrRandomly(House[] arrToFill) {
                Random rnd = new Random();
                for (int i = 0; i < arrToFill.length; i++) {
                        arrToFill[i] = new House(i, rnd.nextInt(100), rnd.nextFloat()*40+20, rnd.nextInt(12), rnd.nextInt(4)+2, streets[rnd.nextInt(streets.length-1)]);
                }
        }

        private House[] createEmptyHouseArray(int amount) {
                return new House[amount];
        }

        private void readFromFile(House[] arrToFill, String fileName) {
                try (Scanner in = new Scanner(new File(fileName+".txt"))) {
                        for (int i = 0; in.hasNext(); i++) {
                                String[] data = in.nextLine().split(" ");
                                String street = data[0];
                                int id = Integer.parseInt(data[1]), num = Integer.parseInt(data[2]), floor = Integer.parseInt(data[3]), room = Integer.parseInt(data[4]);
                                float space = Float.parseFloat(data[5]);
                                arrToFill[i] = new House(id, num, space, floor, room, street);
                        }
                } catch (IOException ex) {
                        System.err.println("Error reading file!");
                }
        }

        private void readFromFileBinary(House[] arrToFill, String fileName) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName+".txt"))) {
                        for (int i = 0; i < arrToFill.length; i++) {
                                String street = (String)in.readObject();
                                int id = in.readInt(), num = in.readInt(); float space = in.readFloat(); int floor = in.readInt(), rooms = in.readInt();
                                arrToFill[i] = new House(id, num, space, floor, rooms, street);
                        }
                } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                }
        }

        private void writeInFile(House[] arrToWrite, String fileName) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt"));) {
                        for (int i = 0; i < arrToWrite.length; i++) {
                                if (arrToWrite[i] == null) break;
                                String str = arrToWrite[i].getData();
                                writer.write(str);
                        }
                        writer.close();
                } catch (IOException ex) {
                        System.err.println("Error writing in file!");
                }
        }

        private void writeInFileBinary(House[] arrToWrite, String fileName) {
                try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName+".txt"))){
                        for (int i = 0; i < arrToWrite.length; i++) {
                                if (arrToWrite[i] == null) break;
                                String street = arrToWrite[i].getStreet();
                                writer.writeObject(street);
                                int id = arrToWrite[i].getId(); int num = arrToWrite[i].getNum(); float space = arrToWrite[i].getSpace(); int floor = arrToWrite[i].getFloor(); int rooms = arrToWrite[i].getRoom();
                                writer.writeInt(id); writer.writeInt(num); writer.writeFloat(space); writer.writeInt(floor); writer.writeInt(rooms);
                        }
                        writer.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}