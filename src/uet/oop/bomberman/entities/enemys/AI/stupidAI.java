package uet.oop.bomberman.entities.enemys.AI;

public class stupidAI extends AI {
    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}
