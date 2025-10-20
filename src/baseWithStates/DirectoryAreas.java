package baseWithStates;

import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryAreas {


  private static Area rootArea;
  private static ArrayList<Door> allDoors;



  public static void makeAreas()
  {
    //creem l'arrel
    Partition building = new Partition("building","...",null);

    //creem les àrees de primer nivell
    Partition basement = new Partition("basement","...", building);
    Partition ground_floor = new Partition("ground_floor","...", building); //dona errors
    Partition floor1 = new Partition("floor1","...", building);
    Partition stairs = new Partition("stairs","...", building); //dona errors
    Partition exterior = new Partition("exterior","...", building);



    Space parking = new Space("parking","...", basement);
    Space hall = new Space("hall","...", ground_floor);
    Space room1 = new Space("room1","...", ground_floor);
    Space room2 = new Space("room2","...", ground_floor);
    Space room3 = new Space("room3","...", floor1);
    Space corridor = new Space("corridor","...", floor1);
    Space IT = new Space("IT","...", floor1);

    Door d1 = new Door("D1", exterior, parking);
    Door d2 = new Door("D2", stairs, parking);
    Door d3 = new Door("D3", exterior, hall);
    Door d4 = new Door("D4", stairs, hall);
    Door d5 = new Door("D5", hall, room1);
    Door d6 = new Door("D6", hall, room2);
    Door d7 = new Door("D7", stairs, corridor);
    Door d8 = new Door("D8", corridor, room3);
    Door d9 = new Door("D9", corridor, IT);

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));

    parking.addDoor(d1);
    parking.addDoor(d2);
    hall.addDoor(d3);
    hall.addDoor(d4);
    room1.addDoor(d5);
    room2.addDoor(d6);
    corridor.addDoor(d7);
    room3.addDoor(d8);
    IT.addDoor(d9);


    basement.addSubArea(parking);
    ground_floor.addSubArea(hall);
    ground_floor.addSubArea(room1);
    ground_floor.addSubArea(room2);
    floor1.addSubArea(room3);
    floor1.addSubArea(corridor);
    floor1.addSubArea(IT);

    building.addSubArea(basement);
    building.addSubArea(ground_floor);
    building.addSubArea(floor1);




    rootArea=building;
  }

  public static Area findAreaById(String id) {
    if (rootArea==null) return null;
    //iniciem cerca recursiva des de l'arrel
    return rootArea.findAreaById(id);

  }

  public static Door findDoorById (String id) {
    return DirectoryDoors.findDoorById(id);
  }

  public static ArrayList<Door> getAllDoors() {

    return allDoors;
  }

}
