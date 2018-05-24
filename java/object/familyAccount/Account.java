import java.io.Serializable;

//Account��

public class Account implements Serializable
{
    private double balance;
    private StringBuffer details = new StringBuffer("��֧\t�˻����\t��֧���\t˵  ��\n");

    public Account() {
        this.balance = 0;
    }
    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public StringBuffer getDetails() {
        return details;
    }

    //��������
    public void incomeProcess(double income, String document) {
        balance += income;
        details = details.append("����\t" + balance + "\t\t" + income + "\t\t" + document + "\n");
    }
    //����֧��
    public void spendingProcess(double spending, String document) {
        balance -= spending;
        details = details.append("֧��\t" + balance + "\t\t" + spending + "\t\t" + document + "\n");
    }
}
