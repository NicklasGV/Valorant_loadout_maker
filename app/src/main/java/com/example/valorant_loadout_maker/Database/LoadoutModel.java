package com.example.valorant_loadout_maker.Database;

public class LoadoutModel {
    int id;
    String loadout_name;
    String agent_name;
    String weapon_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoadout_name() {
        return loadout_name;
    }

    public void setLoadout_name(String loadout_name) {
        this.loadout_name = loadout_name;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getWeapon_name() {
        return weapon_name;
    }

    public void setWeapon_name(String weapon_name) {
        this.weapon_name = weapon_name;
    }
}
