package com.ex.model;

import java.util.ArrayList;

public class Managers
{
    ArrayList<Manager> managers;

    public Managers()
    {
        managers = new ArrayList<Manager>();
    }

    public ArrayList<Manager> getManagers()
    {
        return managers;
    }

    public void setManagers(ArrayList<Manager> managers)
    {
        this.managers = managers;
    }

}
