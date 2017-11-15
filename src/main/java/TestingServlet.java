import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TestingServlet extends HttpServlet {

    Book names = new Book();

    public void init(ServletConfig config) {

        try {

            StringBuilder sb = new StringBuilder();
            BufferedReader in = new BufferedReader(new FileReader("/home/artem/IdeaProjects/maven/src/main/java/text.txt"));
            String s;
            String number = null;
            String name = null;
            int index = 0;
            char c;

            while ((s = in.readLine()) != null) {
                for (int i = 3; i < s.length(); i++) {
                    c = s.charAt(i);
                    if (c == ' ') {
                        index = i;
                        name = sb.toString();
                        break;
                    }
                    sb.append(c);
                }
                sb.delete(0, sb.length());
                for (int i = index + 1; i < s.length(); i++) {
                    c = s.charAt(i);
                    if (c == '<') {
                        number = sb.toString();
                        break;
                    }
                    sb.append(c);
                }
                sb.delete(0, sb.length());
                names.add(name, number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        request.getRequestDispatcher("my.jsp").include(request, response);
        out.println(getMainPage());

        String name_users = request.getParameter("name_users");
        String number_users = request.getParameter("number_users");
        String name_number = request.getParameter("name_number");
        String number_number = request.getParameter("number_number");

        if (name_users != null) {
            if(names.size() == 0)
                names.add(name_users, number_users);
            else{
                boolean flag = false;
                for(int i = 0; i < names.size(); i++){
                    if(names.get_person(i).get_name().equals(name_users)){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    names.add(name_users, number_users);
                }
            }
        }
        if (name_number != null) {
            if(names.size() == 0){
                out.println("<p>No users:(</p>");
            }
            else{
                boolean flag = false;
                for(int i = 0; i < names.size(); i++){
                    if(names.get_person(i).get_number().equals(number_number)){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    names.add_number(name_number, number_number);
                }
            }
        }
        out.println("</html></body>");
        out.close();
    }

    public String getMainPage() throws IOException {

        FileWriter writer = new FileWriter("/home/artem/IdeaProjects/maven/src/main/java/text.txt", false);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < names.size(); i++) {
            Person person = names.get_person(i);

            sb.append("<p>" + person.get_name() + " " + person.get_number() + "</p>\n");
        }

        writer.write(sb.toString());
        writer.flush();

        return sb.toString();
    }
}