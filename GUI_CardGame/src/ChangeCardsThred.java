public class ChangeCardsThred extends Thread {
    MainApplication mp;

    public ChangeCardsThred(MainApplication mp) {
        this.mp = mp;
    }

    @Override
    public void run() {
        mp.disabled=true;
        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }
        mp.cards[0].clicked = false;
        mp.cards[1].clicked = false;
        mp.cards[0].changeImage();
        mp.cards[1].changeImage();
        mp.cards = new Card[2];
        mp.disabled=false;

    }
}
