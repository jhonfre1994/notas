import { TestBed } from '@angular/core/testing';

import { NotaEstudianteService } from './nota-estudiante.service';

describe('NotaEstudianteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NotaEstudianteService = TestBed.get(NotaEstudianteService);
    expect(service).toBeTruthy();
  });
});
