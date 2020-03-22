import { TestBed } from '@angular/core/testing';

import { CursoEstudianteService } from './curso-estudiante.service';

describe('CursoEstudianteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CursoEstudianteService = TestBed.get(CursoEstudianteService);
    expect(service).toBeTruthy();
  });
});
