import { AuditModel } from './audit-model';

export class Category extends AuditModel {
    id?: string;
    name: string;
    description?: string;
    urlImage?: string;
}
