package com.redcored.redball.util;

/**
 * Created by Ipismai on 22.5.2015.
 *
 * The updater class provides means to update UpdateTargets at a
 * constant, predictable rate. It contains a private elapsed time
 * accumulator which keeps track of the time until the next update.
 */
public class StepBasedUpdater {

    private UpdateTarget target;
    private float accumulator = 0;
    private float updateLength = 1;
    private int maximumIterations = 10;

    /**
     * Creates a new StepBasedUpdater and sets it's target and update rate.
     * @param target Update target to receive update calls.
     * @param updatesPerSecond How many times the target is updated per second.
     */
    public StepBasedUpdater(UpdateTarget target, float updatesPerSecond) {
        this.target = target;
        this.updateLength = 1.0f / updatesPerSecond;
    }

    /**
     * Creates a new StepBasedUpdater and sets it's target and update rate, and additionally
     * specifying an upper limit for the maximum number of updates it can perform for
     * each addTime() call.
     * @param target Update target to receive update calls.
     * @param updatesPerSecond How many times the target is updated per second.
     * @param maximumIterations Maximum updates performed at once for each addTime() call.
     */

    public StepBasedUpdater(UpdateTarget target, float updatesPerSecond, int maximumIterations) {
        // TODO: Replace this with BuildConfig.DEBUG based check.
        assert(maximumIterations > 0);

        this.target = target;
        this.updateLength = 1.0f / updatesPerSecond;
        this.maximumIterations = maximumIterations;
    }

    /**
     * Adds the specified amount of time into the updater's accumulator, potentially
     * causing one or multiple updates to be called to this updater's target.
     * @param elapsedTime Time elapsed in seconds since last call to the function.
     */
    public void addTime(float elapsedTime) {
        accumulator += elapsedTime;
        while (accumulator >= updateLength) {
            target.update(updateLength);
            accumulator -= updateLength;
        }
    }

    /**
     * @return A value between 0 and 1 indicating how far into the next update this Updater is.
     */
    public float getInterpolation() {
        return accumulator;
    }

}
