package id.my.abdillah.skripsi.contract.contract;

import id.my.abdillah.skripsi.contract.contract.master.MataKuliahContract;
import id.my.abdillah.skripsi.contract.state.BaseState;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {

    protected Context ctx;
    protected ChaincodeStub stub;

    @BeforeEach
    public void beforeEach() {
        ctx = mock(Context.class);
        stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);
    }
    protected void verifyPutState(ChaincodeStub stub, String expectedId, String expectedJson) {
        byte[] expectedBytes = expectedJson.getBytes(StandardCharsets.UTF_8);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();

                String id = (String) args[0];
                byte[] jsonBytes = (byte[]) args[1];
                String json = new String(jsonBytes, StandardCharsets.UTF_8);

                assertEquals(expectedId, id);
                assertEquals(expectedJson, json);
                assertEquals(true, Arrays.equals(expectedBytes, jsonBytes));

                return null;
            }
        }).when(stub).putState(anyString(), any(byte[].class));
    }

    protected String readFile(String path) {
        try {
            return IOUtils.toString(this.getClass().getResourceAsStream(path), BaseState.CHARSET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected byte[] readFileBytes(String path) {
        return readFile(path).getBytes(StandardCharsets.UTF_8);
    }
}
