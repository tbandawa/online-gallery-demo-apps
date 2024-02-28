//
//  GalleriesView.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/28.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct GalleriesView: View {
    var body: some View {
        NavigationView {
            VStack {
                NavigationLink {
                    GalleryView()
                } label: {
                    Text("Hello, Galleries!")
                }
            }
            .toolbar {
                ToolbarItem(placement: .principal) {
                    Text("Galleries")
                        .font(.largeTitle.bold())
                        .accessibilityAddTraits(.isHeader)
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button {
                        print("search")
                    } label: {
                        Image(systemName: "magnifyingglass")
                    }
                }
             }
        }
    }
}

#Preview {
    GalleriesView()
}
