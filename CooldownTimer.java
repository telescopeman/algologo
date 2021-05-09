public class CooldownTimer {

    private long last_time = 0;
    private long timer_length;

    public CooldownTimer(long length)
    {
        timer_length = length;
        last_time = System.currentTimeMillis();
    }

    public boolean isExpired()
    {
        return (System.currentTimeMillis() - last_time < timer_length);
    }

    public void wake()
    {
        last_time = System.currentTimeMillis();
    }

    public void set_timer_length(long time)
    {
        timer_length = time;
    }
}
