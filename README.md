### Escuela Colombiana de Ingeniería

### ARCN - Arquitectura centrada en el negocio.

#  Taller BDD

De acuerdo al ejemplo de Pagefactory dado en el repositorio bdd-selenium-lab, se implementó un nuevo feature utilizando el ejemplo de [context menu](https://the-internet.herokuapp.com/context_menu). Donde se requirió validar que al momento de dar click derecho a la caja con borde interlineado se mostrara una alerta.

## Empezando

Estas instrucciones te permitirán obtener una copia del proyecto y ejecutarlo en tu máquina local para propósitos de desarrollo y pruebas.

### Prerequisitos

- Java 17 preferiblemente.
- Maven 3.x
- Acceso a una terminal.

### Instalando

Pasos para configurar el entorno de desarrollo:

1. Clona el repositorio del proyecto:

   ```bash
   git clone https://github.com/MiltonGutierrez/ARCN-BDD
   cd ARCN-BDD
   ```
2. Compila el proyecto.

    ```bash
    mvn clean install
    ```
3. 

## Desarrollo del laboratorio.

Se creo el proyecto con el siguiente comando maven:
   ```bash
       mvn archetype:generate -DgroupId=com.eci.myproject -DartifactId=bdd-java -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
   ```

Resultando en la siguiente estructura del proyecto (despues de crear las clases correspondientes y teniendo en cuenta solo las clases de test):
```
   📂 src
   ├── 📂 test
   │   ├── 📂 java
   │   │   ├── 📂 com
   │   │   │   ├── 📂 eci
   │   │   │   │   ├── 📂 myproject
   │   │   │   │   │   ├── 📂 features
   │   │   │   │   │   │   ├── 📄 context_menu.feature
   │   │   │   │   │   │   ├── 📄 google_search.feature
   │   │   │   │   │   ├── 📂 pages
   │   │   │   │   │   │   ├── 📄 ContextMenu.java
   │   │   │   │   │   ├── 📂 runners
   │   │   │   │   │   │   ├── 📄 TestRunner.java
   │   │   │   │   │   ├── 📂 steps
   │   │   │   │   │   │   ├── 📄 ContextMenuSteps.java
   │   │   │   │   │   │   ├── 📄 SearchSteps.java
   │   │   │   │   │   ├── 📄 AppTest.java
```

   
### Definicion de clases y archivos para el Heroku Challenge

#### Archivo: context_menu.feature
La definición de este archivo es de la siguiente manera:

   ```text
   Feature: Heroku Challenge

  Scenario: Right Click on the Context App
    Given I am on the heroku app context menu page
    When I click on the box
    Then I should see the js alert
    And I should see "You selected a context menu" text in the alert
      
   ```
#### Clase: ContextMenu.java (Clase que obtiene el elemento hot-spot)

```java
   public class ContextMenu{
       WebDriver webDriver;
       
       @FindBy(id = "hot-spot")
       WebElement hotSpot;
   
       public ContextMenu(WebDriver driver){
           this.webDriver = driver;
           PageFactory.initElements(driver, this);
       }
   
       public void rightClick(){
           Actions actions = new Actions(webDriver);
           actions.contextClick(hotSpot).perform();
       }
   } 
 ```

#### Clase: ContextMenuSteps.java


```java
   public class ContextMenuSteps {
       private WebDriver driver;
       private ContextMenu contextPage;
       private Alert alert;
   
       @Given("I am on the heroku app context menu page")
       public void i_am_on_the_heroku_app_context_menu_page() {
           try {
               System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
               ChromeOptions options = new ChromeOptions();
               options.addArguments("--headless");
               options.addArguments("--disable-gpu");
               options.addArguments("--no-sandbox");
               options.addArguments("--disable-dev-shm-usage");
               options.addArguments("--remote-allow-origins=*");
               driver = new ChromeDriver(options);
               driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
               driver.get("https://the-internet.herokuapp.com/context_menu");
               contextPage = new ContextMenu(driver);
           } catch (Exception e) {
               e.printStackTrace();
               throw new RuntimeException("Failed to initialize ChromeDriver", e);
           }
       }
   
   
       @When("I click on the box")
       public void i_search_for() {
           contextPage.rightClick();
       }
   
       @Then("I should see the js alert")
       public void i_should_see_the_js_alert() {
           WebDriverWait wait = new WebDriverWait(driver, 10);
           Alert alertDriver = wait.until(ExpectedConditions.alertIsPresent());
           this.alert = alertDriver;
           assert alert != null;
   
       }
   
       @Then("I should see {string} text in the alert")
       public void i_should_see_string_in_the_alert(String text) {
           assert alert.getText().equals(text);
           alert.accept();
       }
   
       @After
       public void tearDown() {
           if (driver != null) {
               driver.quit();
           }
       }
   }    
```

### Ejecución de pruebas

Al momento de ejecutar `mvn test` se prueba que la implementanción se realizó correctamente y se valida con el report.html resultante en la carpeta /target/HtmlReports.

![htmlResport](https://github.com/user-attachments/assets/7bd5c281-8919-4294-9886-cf78aefb28a2)


