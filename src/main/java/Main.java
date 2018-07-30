import powerball.LotteryEngine;
import powerball.Ticket;

public class Main {
    public static void main(String[] args) {
        LotteryEngine lottery = new LotteryEngine();
        for(int i=0; i<550000; i++){
            Ticket ticket = new Ticket();
            ticket.quickPick();
            //System.out.println(ticket.toString());
            lottery.registerTicket(ticket);
        }
        lottery.performDraw();
        lottery.assignWins();
        lottery.printLotteryStatistics();

    }
}
