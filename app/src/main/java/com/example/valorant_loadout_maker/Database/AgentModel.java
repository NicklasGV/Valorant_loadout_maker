package com.example.valorant_loadout_maker.Database;

import java.util.ArrayList;
import java.util.Date;

public class AgentModel {
    public int status;
    public ArrayList<Datum> data;

    public class Ability{
        public String slot;
        public String displayName;
        public String description;
        public String displayIcon;
    }

    public class Role{
        public String uuid;
        public String displayName;
        public String description;
        public String displayIcon;
        public String assetPath;
    }

    public class Root{
        public int status;
        public ArrayList<Datum> data;
    }

    public class RecruitmentData{
        public String counterId;
        public String milestoneId;
        public int milestoneThreshold;
        public boolean useLevelVpCostOverride;
        public int levelVpCostOverride;
        public Date startDate;
        public Date endDate;
    }

    public class Datum{
        public String uuid;
        public String displayName;
        public String description;
        public String developerName;
        public ArrayList<String> characterTags;
        public String displayIcon;
        public String displayIconSmall;
        public String bustPortrait;
        public String fullPortrait;
        public String fullPortraitV2;
        public String killfeedPortrait;
        public String background;
        public ArrayList<String> backgroundGradientColors;
        public String assetPath;
        public boolean isFullPortraitRightFacing;
        public boolean isPlayableCharacter;
        public boolean isAvailableForTest;
        public boolean isBaseContent;
        public Role role;
        public RecruitmentData recruitmentData;
        public ArrayList<Ability> abilities;
        public Object voiceLine;
    }
}
