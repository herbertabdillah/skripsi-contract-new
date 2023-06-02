package id.my.abdillah.skripsi.contract.contract;

import id.my.abdillah.skripsi.contract.state.BaseState;
import org.hyperledger.fabric.contract.Context;

public class BaseContract {
    protected <T extends BaseState> void putState(Context ctx, String id, T state) {
        String newId = state.getClass().getSimpleName() + "." + id;
        ctx.getStub().putState(newId, state.getJsonStringBytes());
    }

    protected <T extends BaseState> T getState(Context ctx, String id, Class<T> clazz) {
        String newId = clazz.getSimpleName() + "." + id;
        byte[] res = ctx.getStub().getState(newId);

        return T.fromJSONString(clazz, res);
    }
}
