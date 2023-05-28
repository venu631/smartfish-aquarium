package com.example.smartaquarium;

public class dataholder {
    String PH,TEMPERATURE,TDS,ULTRASONIC,FEED,user_name,WATER_OUT,password,C_FEED;

    public String getPH() {
        return PH;
    }

    public void setPH(String PH) {
        this.PH = PH;
    }

    public String getTEMPERATURE() {
        return TEMPERATURE;
    }

    public void setTEMPERATURE(String TEMPERATURE) {
        this.TEMPERATURE = TEMPERATURE;
    }

    public String getTDS() {
        return TDS;
    }

    public void setTDS(String TDS) {
        this.TDS = TDS;
    }

    public String getULTRASONIC() {
        return ULTRASONIC;
    }

    public void setULTRASONIC(String ULTRASONIC) {
        this.ULTRASONIC = ULTRASONIC;
    }

    public String getFEED() {
        return FEED;
    }

    public void setFEED(String FEED) {
        this.FEED = FEED;
    }

    public String getuser_name() {
        return user_name;
    }

    public void setUser(String user) {
        this.user_name = user_name;
    }

    public String getWATER_OUT() {
        return WATER_OUT;
    }

    public void setWATER_OUT(String WATER_OUT) {
        this.WATER_OUT = WATER_OUT;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.WATER_OUT = password;
    }

    public String getC_FEED() {
        return C_FEED;
    }

    public void setC_FEED(String C_FEED) {
        this.C_FEED = C_FEED;
    }


    public dataholder(String PH, String TEMPERATURE, String TDS, String ULTRASONIC, String FEED, String user_name, String WATER_OUT, String password,String C_FEED){
        this.PH=PH;
        this.TEMPERATURE=TEMPERATURE;
        this.TDS=TDS;
        this.ULTRASONIC=ULTRASONIC;
        this.FEED=FEED;
        this.user_name=user_name;
        this.WATER_OUT=WATER_OUT;
        this.password=password;
        this.C_FEED=C_FEED;
    }
}
