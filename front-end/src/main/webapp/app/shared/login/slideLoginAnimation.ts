import { animate, group, query, style, transition, trigger } from '@angular/animations';

export const slideLoginAnimation = trigger('fadeAnimation', [
  transition('login => resetPassword', [
    query(
      ':enter, :leave',
      style({
        position: 'absolute',
        width: '100%',
        paddingLeft: '15px',
        paddingRight: '15px'
      }),
      { optional: true }
    ),

    group([
      query(':enter', [style({ left: '100%' }), animate('0.3s ease-in-out', style({ left: '0' }))], { optional: true }),

      query(':leave', [style({ left: '0' }), animate('0.3s ease-in-out', style({ left: '-100%' }))], { optional: true })
    ])
  ]),
  transition('resetPassword => login', [
    query(
      ':enter, :leave',
      style({
        position: 'absolute',
        width: '100%',
        paddingLeft: '15px',
        paddingRight: '15px'
      }),
      { optional: true }
    ),

    group([
      query(':enter', [style({ left: '-100%' }), animate('0.3s ease-in-out', style({ left: '0' }))], { optional: true }),

      query(':leave', [style({ left: '0' }), animate('0.3s ease-in-out', style({ left: '100%' }))], { optional: true })
    ])
  ])
]);
