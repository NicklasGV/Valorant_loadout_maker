package com.example.valorant_loadout_maker.Database;

import java.util.ArrayList;
import java.util.Date;

public class WeaponModel {
    public int status;
    public ArrayList<Datum> data;

    public class AdsStats{
        public double zoomMultiplier;
        public double fireRate;
        public double runSpeedMultiplier;
        public int burstCount;
        public double firstBulletAccuracy;
    }

    public class AirBurstStats{
        public int shotgunPelletCount;
        public double burstDistance;
    }

    public class AltShotgunStats{
        public int shotgunPelletCount;
        public double burstRate;
    }

    public class Chroma{
        public String uuid;
        public String displayName;
        public String displayIcon;
        public String fullRender;
        public String swatch;
        public String streamedVideo;
        public String assetPath;
    }

    public class DamageRange{
        public int rangeStartMeters;
        public int rangeEndMeters;
        public double headDamage;
        public int bodyDamage;
        public double legDamage;
    }

    public class Datum{
        public String uuid;
        public String displayName;
        public String category;
        public String defaultSkinUuid;
        public String displayIcon;
        public String killStreamIcon;
        public String assetPath;
        public WeaponStats weaponStats;
        public ShopData shopData;
        public ArrayList<Skin> skins;
    }

    public class GridPosition{
        public int row;
        public int column;
    }

    public class Level{
        public String uuid;
        public String displayName;
        public String levelItem;
        public String displayIcon;
        public String streamedVideo;
        public String assetPath;
    }

    public class ShopData{
        public int cost;
        public String category;
        public int shopOrderPriority;
        public String categoryText;
        public GridPosition gridPosition;
        public boolean canBeTrashed;
        public Object image;
        public String newImage;
        public Object newImage2;
        public String assetPath;
    }

    public class Skin{
        public String uuid;
        public String displayName;
        public String themeUuid;
        public String contentTierUuid;
        public String displayIcon;
        public String wallpaper;
        public String assetPath;
        public ArrayList<Chroma> chromas;
        public ArrayList<Level> levels;
    }

    public class WeaponStats{
        public double fireRate;
        public int magazineSize;
        public double runSpeedMultiplier;
        public double equipTimeSeconds;
        public double reloadTimeSeconds;
        public double firstBulletAccuracy;
        public int shotgunPelletCount;
        public String wallPenetration;
        public String feature;
        public String fireMode;
        public String altFireType;
        public AdsStats adsStats;
        public AltShotgunStats altShotgunStats;
        public AirBurstStats airBurstStats;
        public ArrayList<DamageRange> damageRanges;
    }
}
