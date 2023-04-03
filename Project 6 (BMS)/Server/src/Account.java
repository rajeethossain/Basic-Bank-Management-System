import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Account {
    int count;
    File accCounter = new File("AccCtr.txt");
    File file = new File("Data.txt");
    File tempFile = new File("Temp.txt");

    void Add(String line) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(getAccCount()+ ":" + line + "\n");
            fileWriter.close();
            updateAccCount();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void Remove(String id) {
        System.out.println(id);
        try {
            Scanner scanner = new Scanner(file);
            FileWriter fileWriter = new FileWriter(tempFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!(line.contains(id))) {
                    fileWriter.write(line + "\n");
                    fileWriter.flush();
                }
            }
            scanner.close();
            fileWriter.close();
            try {
                file.delete();
                tempFile.renameTo(file);
            }
            catch (Exception f){
                System.out.println(f.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void Update(String line) {
        String[] strings = line.split(":");
        Remove(strings[0]);
        Add(line);
    }

    void updateAccCount() {
        try {
            count = getAccCount() + 1;
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write(count+"\n");
            fileWriter.close();
            System.out.println(count);
            try {
                accCounter.delete();
                tempFile.renameTo(accCounter);
            }
            catch (Exception f){
                System.out.println(f.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    int getAccCount(){
        try {
            Scanner fileReader = new Scanner(accCounter);
            count = Integer.parseInt(fileReader.nextLine());
            fileReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return count;
    }
}