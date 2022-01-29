import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {

    private final File file;
    SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyy h.mma");

    public Storage() throws IOException {
        Path filePath = Paths.get(System.getProperty("user.dir"), "duke.txt");
        System.out.println(filePath);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        this.file = new File(String.valueOf(filePath));
    }

    public ArrayList<Task> read() throws IOException, ParseException {

        Scanner sc = new Scanner(this.file);
        ArrayList<Task> taskList = new ArrayList<>();

        while (sc.hasNext()) {
            String[] task = sc.nextLine().trim().split("\t");
            task[0] = task[0].trim();
            task[1] = task[1].trim();
            task[2] = task[2].trim();

            switch (task[0]) {
            case "T":
                taskList.add(new ToDo(task[2], task[1].equals("true")));
                break;
            case "D":
                task[3] = task[3].trim();
                taskList.add(new Deadline(task[2], parse(task[3]), task[1].equals("true")));
                break;
            case "E":
                task[3] = task[3].trim();
                taskList.add(new Event(task[2], parse(task[3]), task[1].equals("true")));
                break;
            }
        }

        return taskList;
    }

    public void write(ArrayList<Task> taskList) throws DukeException {

        try {
            FileWriter fw = new FileWriter(this.file);
            taskList.forEach(task -> {
                try {
                    fw.write(task.toFile() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error when writing to file");
        }
    }

    private Date parse(String date) throws ParseException {
        return ft.parse(date);
    }
}
