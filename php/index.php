<?php
session_start();

$errors = array();

if(isset($_POST['submit'])) {
    $name = $_POST['name'];
    $email = $_POST['email'];
    $subject = $_POST['subject'];
    $body = $_POST['body'];

    $name = htmlspecialchars($name, ENT_QUOTES);
    $email = htmlspecialchars($email, ENT_QUOTES);
    $subject = htmlspecialchars($subject, ENT_QUOTES);
    $body = htmlspecialchars($body, ENT_QUOTES);

    if ($name === "") {
        $errors['name'] = 'No name';
    }

    if ($email === "") {
        $errors['email'] = 'No email';
    }

    if ($body === "") {
        $errors['body'] = 'No body';
    }

    if (count($errors) === 0) {
        $_SESSION['name'] = $name;
        $_SESSION['email'] = $email;
        $_SESSION['subject'] = $subject;
        $_SESSION['body'] = $body;

        header('Location: http://localhost:8000/form2.php');
        exit();
    }
}

if(isset($_GET['action']) && $_GET['action'] === 'edit') {
    $name = $_SESSION['name'];
    $email = $_SESSION['email'];
    $subject = $_SESSION['subject'];
    $body = $_SESSION['body'];
}


?>
<!doctype html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Inquiry</title>
</head>
<body>
<?php
echo "<ul>";
foreach($errors as $e) {
    echo "<li>";
    echo $e;
    echo "</li>";
    }
echo "</ul>";
?>
<form action="index.php" method="POST">
    <table>
        <tr>
            <th>name</th><td><input type="text" name="name" value="<?php if(isset($name)){ echo $name; }?>"></td>
        </tr>
        <tr>
            <th>mail address</th><td><input type="text" name="email" value="<?php if(isset($email)){ echo $email; }?>"></td>
        </tr>
        <tr>
            <th>type of inquiry</th>
            <td>
                <select name="subject">
                    <option value="inquiry_for_work" <?php if(isset($subject) && $subject === "inquiry_for_work") { echo "selected" ;} ?>>inquiry for work</option>
                    <option value="other" <?php if(isset($subject) && $subject === "other") { echo "selected";} ?>>other</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>detail</th>
            <td><textarea name="body" cols="40" rows="10"><?php if(isset($body)){ echo $body; } ?></textarea></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="submit" value="Confirm"></td>
        </tr>
    </table>
</form>
</body>
</html>

