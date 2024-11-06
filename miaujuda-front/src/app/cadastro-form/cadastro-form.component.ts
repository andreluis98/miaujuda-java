import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ServiceService } from '../services/service.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cadastro-form',
  templateUrl: './cadastro-form.component.html',
  styleUrls: ['./cadastro-form.component.css']
})
export class CadastroFormComponent {
  user = {
    nome: '',
    email: '',
    login: '',
    senha: '',
    confirmarSenha: ''
  };
  
  constructor(private http: HttpClient, private registerService: ServiceService, private router: Router) {}
  
  onSubmit() {
    console.log(this.user);
    this.registerUser(
      this.user.nome,
      this.user.email,
      this.user.login,
      this.user.senha
    );
  }

  registerUser(
    name: string,
    email: string,
    username: string,
    password: string
  ) {
    this.registerService.getRegister(
      this.user.nome,
      this.user.email,
      this.user.login,
      this.user.senha
       ).pipe(
      catchError(error => {
        Swal.fire({
          icon: 'error',
          title: 'Erro!',
          text: `${error.error.message}`,
        })
        return ''
      })
    ).subscribe(resp => {
      Swal.fire({
        icon: 'success',
        title: 'Sucesso!',
        text: 'Usuario Registrado com sucesso!',
      }).then((result) => {
        if (result.isConfirmed) {
          this.router.navigate(['/login']);
        }
      });
    })
  }
}
