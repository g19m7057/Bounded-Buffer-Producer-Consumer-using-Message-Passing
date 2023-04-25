import org.jcsp.lang.*;

public class Buffer implements CSProcess
{
    final ChannelOutputInt[] out;
    final AltingChannelInputInt[] request;
    final AltingChannelInputInt[] in;

    int[] buffer;
    int size;
    int tl =  -1; // end of the list
    int hd = -1; // last item of the added to the list
          
    public Buffer (final AltingChannelInputInt[]  in, final AltingChannelInputInt[] request, final ChannelOutputInt[] out, int size){
        this.buffer = new int[size];
        this.request = request;
        this.in = in;
        this.out = out;
        this.size = size;
    }   

    public void run(){
        final Guard[] guards = {in[0], in[1], request[0], request[1]};
        final Alternative alternative = new Alternative(guards);
        int countdown = 4;

        while(countdown != 0)
        {
            int i = alternative.select();
            if(i == 0 || i == 1)
            {
                if(hd < tl+size+1)
                {
                    int item = in[i].read();
                    if (item < 0)
                        countdown--;
                    else
                        {
                            hd++;
                            buffer[hd%size] = item;
                        }
                }
            }
            else
            {
                if(tl < hd)
                {
                    request[i-2].read();
                    tl++;
                    int item = buffer[tl%size];
                    out[i-2].write(item);
                }
                else if (countdown <= 2)
                {
                    request[i-2].read();
                    out[i-2].write(-1);
                    countdown--;
                }
            }
        }
    }
}