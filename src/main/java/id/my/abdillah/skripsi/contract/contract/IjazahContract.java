package id.my.abdillah.skripsi.contract.contract;

import id.my.abdillah.skripsi.contract.state.Krs;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

@Contract(name = "IjazahContract")
@Default
public class IjazahContract implements ContractInterface{
    public IjazahContract(){}

}
