import org.jcsp.lang.*;

/** Consumer class: reads one int from input channel, displays it, then
  * terminates.
  */
public class Consumer implements CSProcess
  { 
    private AltingChannelInputInt channel;
    private ChannelOutputInt request;

    public Consumer (final AltingChannelInputInt channel, final ChannelOutputInt request)
      { 
        this.channel = channel;
        this.request = request;
      } // constructor

    public void run ()
      { 
        int item;
        while(true){
          request.write(0); 
          item = channel.read();
          if(item < 0)
            break;
          System.out.println(item);
        }
        System.out.println(item);
      } // run

  } // class Consumer
