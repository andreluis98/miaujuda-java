import { Component } from '@angular/core';

@Component({
  selector: 'app-cadastro-form',
  templateUrl: './cadastro-form.component.html',
  styleUrls: ['./cadastro-form.component.css']
})
export class CadastroFormComponent {
  user = {
    nome: '',
    sobrenome: '',
    dia: '',
    mes: '',
    ano: '',
    rg: '',
    cpf: '',
    cpf2: '',
    rua: '',
    numero: '',
    bairro: '',
    estado: '',
    cidade: '',
    cep: '',
    cep2: '',
    email: '',
    login: '',
    senha: '',
    confirmarSenha: ''
  };

  onFileSelected(event: any) {
    const file = event.target.files[0];
    console.log(file);
  }

  onSubmit() {
    console.log(this.user);
  }
}
