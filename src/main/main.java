package main;

import house.House;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {

private ArrayList<House> house;
private Scanner scanner = new Scanner(System.in);
private String[] streets = {"Pushkinskaya","Vodoprovodnaya","Shosseynaya","Sovetskaya","Central","Voennaya","Pushkinskaya","Sobornaya","Dekabristov"};

public static void main(String[] args) {
        main prog = new main();
        prog.run();
}

private void run() {
        house = createEmptyHouseArrayList(100);
        UserInput(false);
        room();
        Floor();
        space();
}

private void UserInput(boolean exit) {
        if (exit) return;
        Scanner input = new Scanner(System.in);
        while (true) {
                System.out.println("Введите: \n'a' - чтобы добавить дом,\n'r' - чтобы заполнить массив рандомными домами,\n'p' - чтобы вывести массив,\n'h' - чтобы вывести список квартир на определённом этаже,\n'q' - чтобы вывести список этажей, где квартиры размещены в порядке убывания,\n'v' - чтобы вывести cписок квартир в порядке возростания площади,\n'd' - чтобы удалить дом с индексом,\n'f' - чтобы сохранить массив в файл,\n'b' - чтобы сохранить массив в бинарный файл,\n'g' - чтобы считать массив домов из файла,\n'k' - чтобы считать массив домов из бинарного файла,\n'e' - чтобы выйти с программы");
                String in = input.nextLine();
                if (in.charAt(0) == 'p') {
                        for (int i = 0; i < house.size(); i++) {
                                System.out.println(house.get(i).toString());
                        }
                } else if (in.charAt(0) == 'd') {
                        System.out.println("Введите id элемента для удаления:");
                        String inp = input.nextLine();
                        int number = Integer.parseInt(inp);
                        if (number < 0 || number > house.size()) {System.out.println("Ошибка! Элемента не существует");}
                        else {
                                house.remove(number);
                        }
                } else if (in.charAt(0) == 'a') {
                        System.out.println("Введите данные дома для добавления в формате (id номер_дома площадь этаж кол-во_комнат адресс):");
                        String[] inp = input.nextLine().split(" ");
                        if (inp.length < 6) {System.out.println("Ошибка! Слишком мало данных!");}
                        else {
                                house.add(new House(Integer.parseInt(inp[0]), Integer.parseInt(inp[1]), Float.parseFloat(inp[2]), Integer.parseInt(inp[3]), Integer.parseInt(inp[4]), inp[5]));
                        }
                } else if (in.charAt(0) == 'r') {
                        fillHousesArrRandomly(house, 100);
                } else if(in.charAt(0) == 'f') {
                        System.out.println("Введите имя файла:");
                        String path = input.nextLine();
                        writeInFile(house, path);
                } else if (in.charAt(0) == 'b') {
                        System.out.println("Введите имя бинарного файла:");
                        String path = input.nextLine();
                        writeInFileBinary(house, path);
                } else if (in.charAt(0) == 'g'){
                        System.out.println("Введите имя файла:");
                        String path = input.nextLine();
                        readFromFile(house,path);
                } else if (in.charAt(0) == 'k'){
                        System.out.println("Введите имя бинарного файла:");
                        String path = input.nextLine();
                        readFromFileBinary(house,path);
                } else if (in.charAt(0) == 'v') {
                        VariantD(true);
                } else if (in.charAt(0) == 'q') {
                        VariantE();
                } else if (in.charAt(0) == 'h') {
                        VariantF();
                } else if (in.charAt(0) == 'e') {
                        exit = true;
                        break;
                }
        }
        UserInput(exit);
}

private void room() {
        System.out.println("Input room = ");
        int room1 = scanner.nextInt();
        for (int i = 0; i < house.size(); i++) {
                if (house.get(i).getRoom()==room1) {
                        System.out.println(house.get(i).toString());
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
        for (int i = 0; i < house.size(); i++) {
                if (a <= house.get(i).getFloor() && house.get(i).getFloor() <= b && house.get(i).getRoom() == room2)
                System.out.println(house.get(i).toString());
        }
}

private void VariantD(boolean output) {
        if (output) {
                System.out.println("Список квартир в порядке возростания площади:");
        }
        for (int i = 0; i < house.size(); i++) {
                if (house.get(i) != null) {
                        for (int j = i+1; j < house.size(); j++) {
                                if (house.get(i).getSpace() > house.get(j).getSpace()) {
                                        House buff = house.get(i);
                                        house.set(i, house.get(j));
                                        house.set(j, buff);
                                } else if (house.get(i).getSpace() == house.get(j).getSpace()) {
                                        if (house.get(i).getFloor() > house.get(j).getFloor()) {
                                                House buff = house.get(i);
                                                house.set(i, house.get(j));
                                                house.set(j, buff);
                                        }
                                }
                        }
                }
        }
        if (output) {
                for (int i = 0; i < house.size(); i++) {
                        System.out.println(house.get(i).toString());
                }
        }
}

private void VariantE() {
        int maxFloor = 0;
        for (int i = 0; i < house.size(); i++) {
                if (house.get(i).getFloor() > maxFloor) {
                        maxFloor = house.get(i).getFloor()+1;
                }
        }
        int[] floors = new int[maxFloor];
        int[] amount = new int[maxFloor];
        for (int i = 0; i < floors.length; i++) {
                floors[i] = i+1;
                amount[i] = 0;
        }
        for (int i = 0; i < house.size()-1; i++) {
                amount[house.get(i+1).getFloor()]++;
        }
        for (int i = 0; i < amount.length; i++) {
                for (int j = 0; j < amount.length; j++) {
                        if (amount[i] > amount[j]) {
                                int buff = amount[i];
                                int buff_ = floors[i];
                                amount[i] = amount[j];
                                floors[i] = floors[j];
                                amount[j] = buff;
                                floors[j] = buff_;
                        }
                }
        }
        for (int i = 0; i < amount.length; i++) {
                System.out.println("Floor: "+floors[i]+" houses: "+amount[i]);
        }
}

private void VariantF() {
        System.out.println("Введите этаж: ");
        int floor = scanner.nextInt();
        for (int i = 0; i < house.size(); i++) {
                if (house.get(i) != null) {
                        if (house.get(i).getFloor() == floor) {
                                System.out.println(house.get(i).toString());
                        }
                }
        }
}

private void space() {
        System.out.println("Input space = ");
        float space1 = scanner.nextFloat();
        VariantD(false);
        for (int i = 0; i < house.size(); i++) {
        if (house.get(i).getSpace()>space1) {
        System.out.println(house.get(i).toString());
        }
        }
}

        private void fillHousesArrRandomly(ArrayList<House> arrToFill, int amount) {
                Random rnd = new Random();
                for (int i = 0; i < amount; i++) {
                        arrToFill.add(new House(i, rnd.nextInt(100), rnd.nextFloat()*40+20, rnd.nextInt(12), rnd.nextInt(4)+2, streets[rnd.nextInt(streets.length-1)]));
                }
        }

        private ArrayList<House> createEmptyHouseArrayList(int amount) {
                return new ArrayList<>(100);
        }

        private void readFromFile(ArrayList<House> arrToFill, String fileName) {
                try (Scanner in = new Scanner(new File(fileName+".txt"))) {
                        for (int i = 0; in.hasNext(); i++) {
                                String[] data = in.nextLine().split(" ");
                                String street = data[0];
                                int id = Integer.parseInt(data[1]), num = Integer.parseInt(data[2]), floor = Integer.parseInt(data[3]), room = Integer.parseInt(data[4]);
                                float space = Float.parseFloat(data[5]);
                                arrToFill.add(new House(id, num, space, floor, room, street));
                        }
                } catch (IOException ex) {
                        System.err.println("Error reading file!");
                }
        }

        private void readFromFileBinary(ArrayList<House> arrToFill, String fileName) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName+".txt"))) {
                        int length = in.readInt();
                        for (int i = 0; i < length; i++) {
                                String street = (String)in.readObject();
                                int id = in.readInt(), num = in.readInt(); float space = in.readFloat(); int floor = in.readInt(), rooms = in.readInt();
                                arrToFill.add(new House(id, num, space, floor, rooms, street));
                        }
                } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                }
        }

        private void writeInFile(ArrayList<House> arrToWrite, String fileName) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt"));) {
                        for (int i = 0; i < arrToWrite.size(); i++) {
                                if (arrToWrite.get(i) == null) break;
                                String str = arrToWrite.get(i).getData();
                                writer.write(str);
                        }
                        writer.close();
                } catch (IOException ex) {
                        System.err.println("Error writing in file!");
                }
        }

        private void writeInFileBinary(ArrayList<House> arrToWrite, String fileName) {
                try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName+".txt"))){
                        writer.writeInt(arrToWrite.size());
                        for (int i = 0; i < arrToWrite.size(); i++) {
                                if (arrToWrite.get(i) == null) break;
                                String street = arrToWrite.get(i).getStreet();
                                writer.writeObject(street);
                                int id = arrToWrite.get(i).getId(); int num = arrToWrite.get(i).getNum(); float space = arrToWrite.get(i).getSpace(); int floor = arrToWrite.get(i).getFloor(); int rooms = arrToWrite.get(i).getRoom();
                                writer.writeInt(id); writer.writeInt(num); writer.writeFloat(space); writer.writeInt(floor); writer.writeInt(rooms);
                        }
                        writer.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}