<?php
session_start();

if(isset($_SESSION['name'])) {
    $name = $_SESSION['name'];
    $email = $_SESSION['email'];
    $subject = $_SESSION['subject'];
    $body = $_SESSION['body'];
}

$_SESSION['token'] = base64_encode(openssl_random_pseudo_bytes(48));
$token = htmlspecialchars($_SESSION['token'], ENT_QUOTES);

?>
<!doctype html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Confirmation of inquiry</title>
</head>
<body>
<form action="form3.php" method="POST" >
    <input type="hidden" name="token" value="<?php echo $token ?>">
    <table>
        <tr>
            <th>name</th>
            <td><?php echo $name; ?></td>
        </tr>
        <tr>
            <th>email</th>
            <td><?php echo $email; ?></td>
        </tr>
        <tr>
            <th>subject</th>
            <td><?php echo $subject; ?></td>
        </tr>
        <tr>
            <th>body</th>
            <td><?php echo $body; ?></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="submit" value="post">
            </td>
        </tr>
    </table>

</form>
<p><a href="http://localhost:8000/index.php?action=edit">入力画面へ戻る</a></p>

</body>
</html>
