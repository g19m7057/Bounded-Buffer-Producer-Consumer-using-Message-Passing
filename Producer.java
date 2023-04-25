import org.jcsp.lang.*;
import java.util.Random;

/** Producer class: produces one random integer and sends on
  * output channel, then terminates.
  */
public class Producer implements CSProcess
  { 
    private ChannelOutputInt channel;
    private int begin;
    int end;

    public Producer (final ChannelOutputInt out, int begin, int end)
      { 
        this.channel = out;
        this.begin= begin;
      } // constructor

    public void run ()
      { 
        Random random = new Random();
        for(int i = 0; i < 100; i++)
        {
          int item = (int)(Math.random()*(100)+begin);
          channel.write(item);
        }
        channel.write(-1);
      } // run

  } // class Producer
