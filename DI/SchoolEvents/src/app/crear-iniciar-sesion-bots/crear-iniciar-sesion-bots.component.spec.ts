import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { CrearIniciarSesionBotsComponent } from './crear-iniciar-sesion-bots.component';

describe('CrearIniciarSesionBotsComponent', () => {
  let component: CrearIniciarSesionBotsComponent;
  let fixture: ComponentFixture<CrearIniciarSesionBotsComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CrearIniciarSesionBotsComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(CrearIniciarSesionBotsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
