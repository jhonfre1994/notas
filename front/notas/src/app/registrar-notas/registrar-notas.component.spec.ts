import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarNotasComponent } from './registrar-notas.component';

describe('RegistrarNotasComponent', () => {
  let component: RegistrarNotasComponent;
  let fixture: ComponentFixture<RegistrarNotasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrarNotasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarNotasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
