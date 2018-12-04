package entity;

import java.io.Serializable;

public class DeleteTovar implements Serializable {
    Tovar tovar;

    public Tovar getTovar() {
        return tovar;
    }

    public void setTovar(Tovar tovar) {
        this.tovar = tovar;
    }
}
