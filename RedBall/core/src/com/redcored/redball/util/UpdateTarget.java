package com.redcored.redball.util;

/**
 * Created by Ipismai on 22.5.2015.
 *
 * This interface provides the means for Updaters to call
 * their assigned targets.
 */
public interface UpdateTarget {

    /**
     * Performs any sort of update function. This is called automatically
     * by Updaters at static intervals.
     * @param updateStepLength The interval this is called in seconds.
     */
    public void update(float updateStepLength);

}
