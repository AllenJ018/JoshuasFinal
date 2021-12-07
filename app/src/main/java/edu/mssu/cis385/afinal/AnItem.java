package edu.mssu.cis385.afinal;

public class AnItem {
    private String name;
    private String type;
    private String icon;
    private String rarity;
    private String vendor;

    public AnItem(String name, String type, String rarity, String vendor, String icon){
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.rarity = rarity;
        this.vendor = vendor;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }


    @Override
    public String toString() {
        return "AnItem{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                ", rarity='" + rarity + '\'' +
                ", vendor=" + vendor +
                '}';
    }

    public static String vendorStringConverter(int vendorUnevaluated) {
        //Convert vendor data into presentable currency
        if (vendorUnevaluated < 100)
            return (String.valueOf(vendorUnevaluated) + " Copper");
        else if (vendorUnevaluated < 1000) {
            int vendorHolder = vendorUnevaluated;
            while (vendorHolder > 9) {
                vendorHolder /= 10;
            }
            int silver = vendorHolder;
            return (String.valueOf(silver) + " Silver and " + String.valueOf(vendorUnevaluated - (silver * 100)) + " Copper");
        } else {
            int vendorHolder = vendorUnevaluated;
            while (vendorHolder > 9) {
                vendorHolder /= 10;
            }
            int gold = vendorHolder;
            vendorHolder = vendorUnevaluated - (gold * 1000);
            while (vendorHolder > 9) {
                vendorHolder /= 10;
            }
            int silver = vendorHolder;

            return (String.valueOf(gold) + " Gold, " + String.valueOf(silver) +
                    " Silver and " + String.valueOf(vendorUnevaluated - ((silver * 100) + (gold * 1000))) + " copper");
        }
    }
}

