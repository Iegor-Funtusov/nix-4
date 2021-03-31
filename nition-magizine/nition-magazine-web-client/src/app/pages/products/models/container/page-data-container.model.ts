export interface PageDataContainerModel<DTO> {
  currentPage: number;
  pageSize: number;
  totalElements: number;
  totalPages: number;
  currentShowFromEntries: number;
  currentShowToEntries: number;
  showFirst: boolean;
  showPrevious: boolean;
  showNext: boolean;
  showLast: boolean;
  pageSizeList: number[];
  sort: string;
  order: string;
  items: DTO[];
}
