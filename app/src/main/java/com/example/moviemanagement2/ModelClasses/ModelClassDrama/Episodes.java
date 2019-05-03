package com.example.moviemanagement2.ModelClasses.ModelClassDrama;

public class Episodes  {
    private String episodeName;
    private String episodeDuration;
    private double episodeRattingDouble;
    Drama drama;



    public Episodes(String episodeName, String episodeDuration, double episodeRattingDouble, Drama drama) {
        this.episodeName = episodeName;
        this.episodeDuration = episodeDuration;
        this.episodeRattingDouble = episodeRattingDouble;
        this.drama  = drama;
    }

    public Episodes() {
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getEpisodeDuration() {
        return episodeDuration;
    }

    public void setEpisodeDuration(String episodeDuration) {
        this.episodeDuration = episodeDuration;
    }


    public double getEpisodeRattingDouble() {
        return episodeRattingDouble;
    }

    public void setEpisodeRattingDouble(double episodeRattingDouble) {
        this.episodeRattingDouble = episodeRattingDouble;
    }

}
