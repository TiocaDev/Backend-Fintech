<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cadastro</title>
  <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@include file="header.jsp" %>
<main class="bg-light d-flex justify-content-center align-items-center py-4">
  <div class="card shadow p-4" style="max-width: 400px; width: 100%;">
    <h1 class="h4 text-center mb-4">Crie sua Conta</h1>

    <form action="cadastro" method="POST" onsubmit="return validarSenhas()">
      <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" id="nome" name="nome" placeholder="Seu nome completo" required
        >
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="Seu email" required
        >
      </div>

      <div class="row mb-3">
        <div class="col">
          <label for="senha" class="form-label">Senha</label>
          <input type="password" class="form-control" id="senha" name="senha" placeholder="Sua senha" required>
        </div>
        <div class="col">
          <label for="confirmarSenha" class="form-label">Confirmar Senha</label>
          <input type="password" class="form-control" id="confirmarSenha" name="confirmarSenha"
                 placeholder="Confirme sua senha" required>
        </div>
      </div>

      <div class="mb-3">
        <label for="genero" class="form-label">Gênero</label>
        <select class="form-select" id="genero" name="genero" required>
          <option value="M">Masculino</option>
          <option value="F">Feminino</option>
          <option value="O">Outro</option>
        </select>
      </div>

      <div class="mb-3">
        <label for="data_nascimento" class="form-label">Data de Nascimento</label>
        <input type="date" class="form-control" id="data_nascimento" name="data_nascimento" required
               value="<%= request.getParameter("data_nascimento") != null ? request.getParameter("data_nascimento") : "" %>">
      </div>

      <button type="submit" class="btn btn-primary w-100">Cadastrar</button>
    </form>

    <div class="d-flex gap-2 mt-3">
      <a href="#" class="btn btn-danger flex-fill">Google</a>
      <a href="#" class="btn btn-primary flex-fill">Facebook</a>
    </div>

    <div class="text-center mt-3">
      <small>Já possui uma conta? <a href="./loginUser.jsp" class="text-primary">Entrar</a></small>
    </div>
  </div>
</main>
<%@include file="footer.jsp" %>


<script src="./resources/js/bootstrap.bundle.min.js"></script>
<script>
    function validarSenhas() {
        const senha = document.getElementById('senha').value;
        const confirmarSenha = document.getElementById('confirmarSenha').value;

        if (senha !== confirmarSenha) {
            alert('As senhas não coincidem. Por favor, verifique.');
            return false;
        }
        return true;
    }
</script>
</body>
</html>
