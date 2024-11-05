import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginScreenComponent } from './login/login-screen/login-screen.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home/home.component';
import { MenuComponent } from './menu/menu/menu.component';
import { CadastroFormComponent } from './cadastro-form/cadastro-form.component';
import { PetOcorrenciaComponent } from './pet-ocorrencia/pet-ocorrencia.component';
import { DoeComponent } from './doe/doe/doe.component';
import { ListarOcorrenciaComponent } from './listar-ocorrencia/listar-ocorrencia/listar-ocorrencia.component';
import { DetalhesComponent } from './detalhes/detalhes/detalhes.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginScreenComponent,
    HomeComponent,
    MenuComponent,
    CadastroFormComponent,
    PetOcorrenciaComponent,
    DoeComponent,
    ListarOcorrenciaComponent,
    DetalhesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
