interface Command {
    void execute();
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }
}

class Light {
    public void on() {
        System.out.println("Light is ON");
    }
}

class Fan {
    public void on() {
        System.out.println("Fan is ON");
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

public class CommandPattern{
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        Command lightOnCommand = new LightOnCommand(livingRoomLight);
        Command fanOnCommand = new FanOnCommand(ceilingFan);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(lightOnCommand);
        remote.pressButton();

        remote.setCommand(fanOnCommand);
        remote.pressButton();
    }
}
