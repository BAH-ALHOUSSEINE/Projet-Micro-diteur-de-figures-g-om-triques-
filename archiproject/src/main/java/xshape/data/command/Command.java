package xshape.data.command;

public interface Command {
    void  execute();
    void undo();

    void redo();
    
} 

