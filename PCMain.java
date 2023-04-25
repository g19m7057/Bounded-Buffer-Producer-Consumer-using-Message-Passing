import org.jcsp.lang.*;

/** Main program class for Producer/Consumer example.
  * Sets up channel, creates one of each process then
  * executes them in parallel, using JCSP.
  */
public final class PCMain
  {
    public static void main (String[] args)
      { new PCMain();
      } // main

    public PCMain ()
      { // Create channel object
        final One2OneChannelInt[] channelProducer = {Channel.one2oneInt(),  Channel.one2oneInt()};
        final One2OneChannelInt[] channelRequest = {Channel.one2oneInt(),  Channel.one2oneInt()};
        final One2OneChannelInt[] channelConsumer = {Channel.one2oneInt(),  Channel.one2oneInt()};

        final AltingChannelInputInt[] bufferProducer = {channelProducer[0].in(), channelProducer[1].in()};
        final ChannelOutputInt[] bufferConsumer = {channelConsumer[0].out(), channelConsumer[1].out()};
        final AltingChannelInputInt[] bufferRequest = {channelRequest[0].in(), channelRequest[1].in()};

        final int bufferSize = 100;
        // Create and run parallel construct with a list of processes
        CSProcess[] procList = {  
                                  new Producer(channelProducer[0].out(), 0, 1000), new Producer(channelProducer[1].out(), 1000, 2000), 
                                  new Buffer(bufferProducer, bufferRequest, bufferConsumer, bufferSize),
                                  new Consumer(channelConsumer[0].in(), channelRequest[0].out()), new Consumer(channelConsumer[1].in(), channelRequest[1].out())
        }; // Processes
        
        Parallel par = new Parallel(procList); // PAR construct
        par.run(); // Execute processes in parallel
      } // PCMain constructor

  } // class PCMain


  // 91
// 1048
// 75
// 1035
// 16
// 1095
// 1049
// 57
// 42
// 1063
// 54
// 1092
// 64
// 1069
// 37
// 1001
// 60
// 1009
// 5
// 1032
// 14
// 1015
// 93
// 1072
// 96
// 1088
// 14
// 1094
// 82
// 1057
// 32
// 1008
// 55
// 1034
// 44
// 18
// 1025
// 39
// 1062
// 72
// 1097
// 5
// 1099
// 89
// 1058
// 93
// 1067
// 20
// 1044
// 43
// 1031
// 40
// 1066
// 4
// 1029
// 87
// 1057
// 25
// 1094
// 72
// 1059
// 84
// 1072
// 82
// 1026
// 3
// 1042
/**

  as the producer finishes producing an integer it inputs it into the 
  buffer process without waiting for the consumer process to request a value
  from it,
  the consumer can consumer intgers from the buffer when it can instead of
  waiting for the producer to produce a value.
 */