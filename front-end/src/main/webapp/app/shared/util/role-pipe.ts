import { Pipe, PipeTransform } from '@angular/core';
import { ADMIN, USER } from 'app/shared/constants/role.constants';

@Pipe({ name: 'roleParserCssClass' })
export class RolePipeCssClass implements PipeTransform {
  transform(role: string): string | null {
    switch (role) {
      case ADMIN:
        return 'admin-label';
      case USER:
        return 'user-label';
      default:
        return null;
    }
  }
}

@Pipe({ name: 'roleParserTranslation' })
export class RolePipeTranslation implements PipeTransform {
  transform(role: string): string | null {
    switch (role) {
      case ADMIN:
        return 'global.role.admin';
      case USER:
        return 'global.role.user';
      default:
        return null;
    }
  }
}
