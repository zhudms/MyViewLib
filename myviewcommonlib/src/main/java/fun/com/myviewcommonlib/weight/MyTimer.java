package fun.com.myviewcommonlib.weight;


import android.os.CountDownTimer;

/**
 * The type My timer.
 */
public class MyTimer extends CountDownTimer {

    private boolean isTimerRunning=false;

    /**
     * The interface On countdown lisener.
     */
    public interface OnCountdownLisener {
        /**
         * On down.
         */
        void onDown();

        /**
         * On tick.
         */
        void onTick();
    }

    private OnCountdownLisener lisener;

    /**
     * Sets on countdown lisener.
     *
     * @param countdownLisener the countdown lisener
     */
    public void setOnCountdownLisener(OnCountdownLisener countdownLisener) {
        this.lisener = countdownLisener;
    }

    /**
     * Instantiates a new My timer.
     *
     * @param millisInFuture    the millis in future
     * @param countDownInterval the count down interval
     */
    public MyTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        // TODO Auto-generated constructor stub

    }


    @Override
    public void onTick(long millisUntilFinished) {
        // TODO Auto-generated method stub
        if (this.lisener != null) {
            lisener.onTick();
        }
    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub
        if (this.lisener != null) {
            lisener.onDown();
        }
        isTimerRunning = false;
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public void setTimerRunning(boolean timerRunning) {
        isTimerRunning = timerRunning;
    }
}
