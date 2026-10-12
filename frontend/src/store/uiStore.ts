import { create } from 'zustand';

interface UiState {
  sidebarOpen: boolean;
  darkMode: boolean;
  setSidebarOpen: (open: boolean) => void;
  toggleDarkMode: () => void;
}

export const useUiStore = create<UiState>((set) => ({
  sidebarOpen: true,
  darkMode: false,
  setSidebarOpen: (open) => set({ sidebarOpen: open }),
  toggleDarkMode: () => {
    set((state) => {
      const newDarkMode = !state.darkMode;
      if (newDarkMode) {
        document.documentElement.classList.add('dark');
      } else {
        document.documentElement.classList.remove('dark');
      }
      localStorage.setItem('darkMode', String(newDarkMode));
      return { darkMode: newDarkMode };
    });
  },
}));
