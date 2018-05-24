//����

import java.util.Scanner;
import java.io.*;

public class FamilyAccount
{
    Scanner scanner = new Scanner(System.in);
    Account account;
    Account account2 = new Account(10000);  //���������ļ��ĳ�ʼ��
    File file = new File("account_data.txt");

    //main������ȡ���������һ��ʵ��������ʵ���ϵ�������������ʼ
    public static void main(String[] args) {
        FamilyAccount unit = new FamilyAccount();
        unit.mainMenu();
    }
    
    /*ʵ���ϵ���������ͳ�����෽��*/
    public void mainMenu() {		
    	
    	//���ȳ��Զ���Account����
    	try {
    		//��������ļ������ڣ��򴴽�����ʼ��
    		if (!file.exists()) {
    			file.createNewFile();
    			System.out.println("�״�ʹ�ã������ļ��ɹ���");
    			fileInitialize();  //��ʼ���ļ�
    		}
    		//�������
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
    		System.out.println("�Ѷ���");
    		account = (Account)in.readObject();
    		in.close();
    		
    	} catch (Exception e) {
			System.out.println("Something error when creating file or reading file...\nExited.");
			System.exit(0);
		}
    	
        int option;
        boolean loopFlag = true;
        
        //loopFlagΪ��ʱѭ������
        while(loopFlag == true) {
            //��ʾ�˵���������ʾ
            menu();
            //��ȡ�û������ѡ��
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
            } else {
                scanner.next();
                option = -1;
            }
            //����ѡ�������Ӧ����
            switch(option) {
                case 1: System.out.println(account.getDetails()); break;  //ֱ�ӵ���getDetails()���������ϸ
                case 2: income(); break;          //����income()��������������
                case 3: spending(); break;        //����spending()����������֧��
                case 4: loopFlag = quit(); break; //quit()���������û����뷵�ز���ֵ, ���丳��loopFlag
                default: System.out.println("��Ҫһ��ѡ��(1-4)�������ԡ�"); break;
            }
        }
        
        //�����ļ�
        try {
        	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        	out.writeObject(account);
        	System.out.println("�ѱ���");
        	out.close();
        } catch (Exception e) {
        	System.out.println("Something error when writing file...\nExited.");
		}
        
        System.out.println("���˳���");  //����ɹ��˳�����ʾ
    }

    //�ļ���ʼ��
    private void fileInitialize() {
    	try {
        	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        	out.writeObject(account2);
        	System.out.println("��ʼ���ɹ���");
        	out.close();
        } catch (Exception e) {
        	System.out.println("Something error when initialize file...\nExited.");
        	System.exit(0);
		}
    }
    
    //��ӡ�˵�
    public void menu() {
        System.out.println("-----------------��ͥ��֧�������-----------------");
        System.out.println();
        System.out.println("                   1 ��֧��ϸ");
        System.out.println("                   2 �Ǽ�����");
        System.out.println("                   3 �Ǽ�֧��");
        System.out.println("                   4 ��    ��");
        System.out.println();
        System.out.print("        ��ѡ��(1-4)��");
    }

    //��������
    public void income() {
        double money = 0;
        String document;

        System.out.print("���������");
        money = readDouble(false);  //��������Ϊ�˷����ڲ�һ�����ܵĿ��أ��˴��ر�
        System.out.print("��������˵����");
        document = readString(); 
        account.incomeProcess(money, document);
    }

    //����֧��
    public void spending() {
        double money;
        String document;

        System.out.print("����֧����");
        money = readDouble(true);  //���ܿ��ش�
        System.out.print("����֧��˵����");
        document = readString();
        account.spendingProcess(money, document);
    }

    //�ж��Ƿ��˳������򷵻�false
    public boolean quit() {
        String temp;

        System.out.print("ȷ���Ƿ��˳�(Y/N)��");
        temp = scanner.next();
        if (temp.equals("Y") || temp.equals("y")) {
            return false;
        } else {
            return true;
        }
    }

    //ȷ����ù淶�Ľ������
    public double readDouble(boolean optionFlag) {
        double money = 0;
        while(scanner.hasNext()) {
            try {
                money = Double.parseDouble( scanner.next() );
                if (money < 0) {
                    System.out.print("����Ϊ���������ԣ�");
                }
                else if (optionFlag && money > account.getBalance()) {
                //���optionFlagΪ�棬��ִ�д˴���飬�ж�����Ľ���Ƿ����������˴���鲻ִ��
                    System.out.print("����Ϊ�����˻��������ԣ�");
                }
                else {
                    break;
                }
            } catch(NumberFormatException e) {
                System.out.print("���ӦΪ���֣�������:");
            }
        }
        return money;
    }

    //ȷ����ù淶����ϸ����
    public String readString() {
        String document = "";
        while(scanner.hasNext()) {
            document = scanner.next();
            if (document.length() < 1 || document.length() > 8) {
                System.out.print("�����ֶι��������������룺");
            } else {
                break;
            }
        }
        return document;
    }
}
