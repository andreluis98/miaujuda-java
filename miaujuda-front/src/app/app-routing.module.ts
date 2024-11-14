import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginScreenComponent } from './login/login-screen/login-screen.component';
import { HomeComponent } from './home/home/home.component';
import { CadastroFormComponent } from './cadastro-form/cadastro-form.component';
import { PetOcorrenciaComponent } from './pet-ocorrencia/pet-ocorrencia.component';
import { DoeComponent } from './doe/doe/doe.component';
import { ListarOcorrenciaComponent } from './listar-ocorrencia/listar-ocorrencia/listar-ocorrencia.component';
import { DetalhesComponent } from './detalhes/detalhes/detalhes.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginScreenComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'cadastro', component: CadastroFormComponent },
  { path: 'ocorrencia', component: PetOcorrenciaComponent, canActivate: [AuthGuard] },
  { path: 'doe', component: DoeComponent, canActivate: [AuthGuard] },
  { path: 'ocorrencias', component: ListarOcorrenciaComponent, canActivate: [AuthGuard] },
  { path: 'ocorrencias/:id', component: DetalhesComponent, canActivate: [AuthGuard] } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
