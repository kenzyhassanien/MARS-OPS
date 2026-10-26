/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public class StorageFacilities extends Facility{

    public StorageFacilities(String location, int capacity, boolean operationalStatus, List<Resources> resources, List<String> equipment) {
        super(location, capacity, operationalStatus, resources, equipment);
    }
    
}
