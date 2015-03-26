package com.next.volley.model;

/**
 * Created by NeXT on 15-3-26.
 */
public class WeatherModel {

    private Test weatherInfo;

    public Test getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(Test weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "weatherInfo=" + weatherInfo.toString() +
                '}';
    }

    public class Test{

        private String city;
        private String cityid;
        private String temp;
        private String WD;
        private String WS;
        private int WSE;
        private String time;
        private int isReadar;
        private String Radar;
        private String njd;
        private String qy;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWD() {
            return WD;
        }

        public void setWD(String WD) {
            this.WD = WD;
        }

        public String getWS() {
            return WS;
        }

        public void setWS(String WS) {
            this.WS = WS;
        }

        public int getWSE() {
            return WSE;
        }

        public void setWSE(int WSE) {
            this.WSE = WSE;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getIsReadar() {
            return isReadar;
        }

        public void setIsReadar(int isReadar) {
            this.isReadar = isReadar;
        }

        public String getRadar() {
            return Radar;
        }

        public void setRadar(String radar) {
            Radar = radar;
        }

        public String getNjd() {
            return njd;
        }

        public void setNjd(String njd) {
            this.njd = njd;
        }

        public String getQy() {
            return qy;
        }

        public void setQy(String qy) {
            this.qy = qy;
        }

        @Override
        public String toString() {
            return  "city='" + city + '\'' +
                    ", cityid='" + cityid + '\'' +
                    ", temp='" + temp + '\'' +
                    ", WD='" + WD + '\'' +
                    ", WS='" + WS + '\'' +
                    ", WSE=" + WSE +
                    ", time='" + time + '\'' +
                    ", isReadar=" + isReadar +
                    ", Radar='" + Radar + '\'' +
                    ", njd='" + njd + '\'' +
                    ", qy='" + qy + '\'';
        }
    }
}

