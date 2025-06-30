package com.fittrack.api.dto.request;

public class ExerciseLogRequest {
    private Long exerciseId;
    private Integer sets;
    private Integer reps;
    private Double weight;
    private Integer duration;

    // Getters
    public Long getExerciseId() {
        return exerciseId;
    }

    public Integer getSets() {
        return sets;
    }

    public Integer getReps() {
        return reps;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getDuration() {
        return duration;
    }

    // Setters
    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    // toString()
    @Override
    public String toString() {
        return "ExerciseLogRequest{" +
                "exerciseId=" + exerciseId +
                ", sets=" + sets +
                ", reps=" + reps +
                ", weight=" + weight +
                ", duration=" + duration +
                '}';
    }
}