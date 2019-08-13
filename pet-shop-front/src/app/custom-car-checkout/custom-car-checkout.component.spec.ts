import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomCarCheckoutComponent } from './custom-car-checkout.component';

describe('CustomCarCheckoutComponent', () => {
  let component: CustomCarCheckoutComponent;
  let fixture: ComponentFixture<CustomCarCheckoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomCarCheckoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomCarCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
